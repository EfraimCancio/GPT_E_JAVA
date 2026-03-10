package br.com.alura.gptjava;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class TestaIntegracao {

    public static void main(String[] args) {
        String chatUser = "Gere 5 produtos produtos";
        String chatSystem = "Voçê é um criador de produtos fictícios para um ecommerce, gere os produtos com nome, descrição e preço. Todos os produtos devem ser do nicho de pets. Gere os produtos em formato json, seguindo o exemplo: [{\"nome\": \"Ração para Cães\", \"descrição\": \"Ração de alta qualidade para cães de todas as idades.\", \"preço\": 150.00}]";

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
                .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);

        chatCompletion.choices().forEach(System.out::println);
    }
    }
