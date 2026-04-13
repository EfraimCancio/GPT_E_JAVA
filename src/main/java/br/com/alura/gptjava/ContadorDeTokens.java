package br.com.alura.gptjava;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.*;
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;

public class ContadorDeTokens {

    public static void main(String[] args) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncoding(ModelType.GPT_3_5_TURBO.getEncodingType());
        IntArrayList encoded = enc.encode("Identifique o perfil de compra de cada cliente");

        Integer qtdTokens = encoded.size();
        System.out.println("Quantidade de tokens: "+qtdTokens);

        BigDecimal custo = new BigDecimal(qtdTokens)
                .divide(new BigDecimal(1000))
                .multiply(new BigDecimal(0.0010));
        System.out.println("Custo da requisição: "+ custo);

        System.out.println("Custo aproximado: "+custo);
        String decoded = enc.decode(encoded);
        // decoded = "This is a sample sentence."

        // Or get the tokenizer based on the model type
        Encoding secondEnc = registry.getEncodingForModel(ModelType.TEXT_EMBEDDING_ADA_002);
        // enc == secondEnc
    }
}
