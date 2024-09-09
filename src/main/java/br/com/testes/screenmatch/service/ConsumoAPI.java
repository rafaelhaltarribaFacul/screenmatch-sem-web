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
        this.apiKey = ApiKeyLoader.carregarChaveApi("api_key.txt"); // crie um arquivo api_key.txt que contenha a sua chave da API

        if (this.apiKey.isEmpty()) {
            throw new RuntimeException("Chave da API não encontrada ou não carregada corretamente.");
        }
    }

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco + "&apikey=" + this.apiKey))
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
