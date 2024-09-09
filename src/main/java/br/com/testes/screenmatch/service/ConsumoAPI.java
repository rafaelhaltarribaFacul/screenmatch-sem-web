package br.com.testes.screenmatch.service;

import br.com.testes.screenmatch.security.ApiKeyLoader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private String apiKey;

    public ConsumoAPI() {
        // Carregar a chave da API usando ApiKeyLoader
        this.apiKey = ApiKeyLoader.carregarChaveApi("api_key.txt"); // Certifique-se de que o caminho esteja correto

        // Verifica se a chave foi carregada corretamente
        if (this.apiKey.isEmpty()) {
            throw new RuntimeException("Chave da API não encontrada ou não carregada corretamente.");
        }
    }

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco + "&apikey=" + this.apiKey)) // Adiciona a chave da API ao final da URL
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao enviar a solicitação HTTP: " + e.getMessage(), e);
        }

        return response.body();
    }
}
