<template>
  <div id="app">
    <nav class="center">
      <div class="nav-wrapper  darken-1">
        <a href="#" class="brand-logo center">Projetos</a>
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
          <input type="text" placeholder="nome" id="nome" v-model="projeto.nome">
          
          <label for="status">Nome</label>
          <input type="text" placeholder="status" id="status" v-model="projeto.status">
          

          <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

      </form>

      <table>

        <thead>

          <tr>
            <th>NOME</th>
            <th>STATUS</th>
           
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

          <tr  v-for="projeto of projetos" :key="projeto.id">

            <td>{{ projeto.nome }}</td>
            <td>{{ projeto.status }}</td>
           
            <td>
              <button @click="editar(projeto)" class="waves-effect btn-small blue darken-1"><i class="material-icons">create</i></button>
              <button @click="remover(projeto)"  class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
            </td>

          </tr>

        </tbody>
      
      </table>

    </div>

  </div>
</template>


<script>

import Projeto from '../services/projetos'

export default{

  data(){
    return{ 
      projeto:{
        id:'',
        nome:'',
        status:'',
      //  valor:''
     },
      projetos:[],
      errors:[]
    }
  },
  
  mounted(){
    this.listar()
  },

  methods:{
    
    listar(){
      Projeto.listar().then( resposta=>{
      this.projetos = resposta.data
      console.log(this.projetos)
      } )
    },

    salvar(){
      if(!this.projeto.id){
        Projeto.salvar(this.projeto).then(resposta=> {
        this.projeto ={}
        alert("Salvo com sucesso")
        this.resposta=resposta
        this.listar()
        this.errors=[]
         }).catch(e=>{
         this.errors = e.response.data.errors
       })
      }
       else{
        Projeto.atualizar(this.projeto).then(resposta=> {
        this.projeto ={}
        alert("Atualziado com sucesso")
        this.resposta=resposta
        this.listar()
        this.errors=[]
         }).catch(e=>{
         this.errors = e.response.data.errors
       })
      }
    },

    editar(projeto){
      this.projeto=projeto
    },

    
    remover(projeto){
     if (confirm('Deseja Excluir o Produto?')){
      Projeto.excluir(projeto).then(resposta=>{
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




