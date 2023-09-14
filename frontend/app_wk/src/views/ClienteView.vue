<template>
  <div id="app">
    <nav class="center">
      <div class="nav-wrapper  darken-1">
        <a href="#" class="brand-logo center">Clientes</a>
      </div>
    </nav>

    <div class="container">

      <ul>
        <li v-for="(erro,index) of errors" :key="index">
           campo <b>{{ erro.field}}</b>  - {{ erro.defaultMessage }}
        </li>
      </ul>

      <form @submit.prevent="salvar">

          <label for="nome">Nome</label>
          <input type="text" placeholder="nome" id="nome" v-model="cliente.nome">

          <label for="email">Email</label>
          <input type="email" placeholder="email" id="email" v-model="cliente.email">
          
          <label for="nome">Nome</label>
          <input type="number" placeholder="telefone" id="telefone" v-model="cliente.telefone">
          

          
          <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

      </form>

      <table>

        <thead>

          <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
           
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

          <tr  v-for="cliente of clientes" :key="cliente.id">

            <td>{{cliente.nome }}</td>
            <td>{{ cliente.email }}</td>
            <td>{{ cliente.telefone }}</td>
           
            <td>
              <button @click="editar(cliente)" class="waves-effect btn-small blue darken-1"><i class="material-icons">create</i></button>
              <button @click="remover(cliente)"  class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
            </td>

          </tr>

        </tbody>
      
      </table>

    </div>

  </div>
</template>


<script>

import Cliente from '../services/clientes'

export default{

  data(){
    return{ 
      cliente:{
        id:'',
        nome:'',
        email:'',
      telefone: ''
     },
      clientes:[],
      errors:[]
    }
  },
  
  mounted(){
    this.listar()
  },

  methods:{
    
    listar(){
      Cliente.listar().then( resposta=>{
      this.clientes = resposta.data
      console.log(this.clientes)
      } )
    },

    salvar(){
      if(!this.cliente.id){
        Cliente.salvar(this.cliente).then(resposta=> {
        this.cliente ={}
        alert("Salvo com sucesso")
        this.resposta=resposta
        this.listar()
        this.errors=[]
         }).catch(e=>{
         this.errors = e.response.data.errors
       })
      }
       else{
        Cliente.atualizar(this.cliente).then(resposta=> {
        this.cliente ={}
        alert("Atualizando com sucesso")
        this.resposta=resposta
        this.listar()
        this.errors=[]
         }).catch(e=>{
         this.errors = e.response.data.errors
       })
      }
    },

    editar(cliente){
      this.cliente=cliente
    },

    
    remover(cliente){
     if (confirm('Deseja Excluir o Cliente?')){
      Cliente.excluir(cliente).then(resposta=>{
        this.resposta=resposta
        this.listar();
        this.errors=[]
      }).catch(e=>{
        this.errors = e.response.data.errors
      })
     }
      }
   }

}

</script>