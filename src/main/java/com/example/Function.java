package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;


public class Function {

    class Cidade {
        private Long id;
        private String nome;
        private Estado estado;

        public Cidade(Long id, String nome, Estado estado){
            this.id = id;
            this.nome = nome;
            this.estado = estado;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
        
        public Estado getEstado() {
            return estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }
    }

    class Estado {
        private Long id;
        private String nome;

        public Estado(String nome){
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

    }

    @FunctionName("funcaolercidade")
    public List<Cidade> ler(
        @HttpTrigger(
            name = "restlercidade",
            methods = {HttpMethod.GET},
            route = "cidade"
        )
        String x
    ){
        return Stream.of(
            new Cidade(1L, "Atibaia", new Estado("SP")),
            new Cidade(1L, "Cornélio Procópio", new Estado("PR")),
            new Cidade(1L, "Londrina", new Estado("PR"))
        ).collect(Collectors.toList());
    }

    @FunctionName("funcaocriarcidade")
    public Cidade criar(
        @HttpTrigger(
            name = "restcriarcidade",
            methods = {HttpMethod.POST},
            route = "cidade"
        )
        Cidade c
    ){
        c.setId(new Long(1));
        return c;
    }

    @FunctionName("funcaoalterarcidade")
    public Cidade alterar(
        @HttpTrigger(
            name = "restalterarcidade",
            methods = {HttpMethod.PUT},
            route = "cidade"
        )
        Cidade c
    ){
        c.setNome(c.getNome() + " - updated");
        return c;
    }

    @FunctionName("funcaoapagarcidade")
    public int apagar(
        @HttpTrigger(
            name = "restapagarcidade",
            methods = {HttpMethod.DELETE},
            route = "cidade/{id}"
        )
        @BindingName ("id") Long id
    ){
        System.out.println(String.format("ID: %d", id));
        return 200;
    }
    
}