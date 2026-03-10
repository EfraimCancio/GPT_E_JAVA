package br.com.alura.gptjava;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class CategorizadorDeProdutos {

    public static void main(String[] args) {
        String chatUser = "Escova de dentes";
        String chatSystem = "Voçê é um categorizador de produtos";

        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("A variável de ambiente OPENAI_API_KEY não está configurada!");
        }

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(chatUser)
                .addSystemMessage(chatSystem)
                .model(ChatModel.GPT_5_2)
                .n(5)
                .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);

        chatCompletion.choices().forEach(c -> {
            System.out.println("Categoria sugerida: " + c.message().content());
            System.out.println("----------------------------------------------------------------");
        });
    }
}
