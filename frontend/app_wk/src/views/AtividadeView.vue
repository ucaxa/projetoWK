<template>
  <div id="app">
    <nav class="center">
      <div class="nav-wrapper  darken-1">
        <a href="#" class="brand-logo center">Atividades</a>
      </div>
    </nav>

    <div class="container">

      <ul>
        <li v-for="(erro,index) of errors" :key="index">
           campo <b>{{ erro.field}}</b>  - {{ erro.defaultMessage }}
        </li>
      </ul>

      <form @submit.prevent="salvar">

          <label for="nome"> Descrição</label>
          <input type="text" placeholder="nome" id="nome" v-model="atividade.descricao">

          <label for="projeto"> Projeto</label>
          <input type="text" placeholder="projeto" id="projeto" v-model="atividade.projeto">
          
          <label for="cliente"> Cliente</label>
          <input type="text" placeholder="cliente" id="cliente" v-model="atividade.cliente">
          
          

        <select>
         <option value="" disabled selected>Selecione o projeto</option>
         <option value="1">Option 1</option>
         <option value="2">Option 2</option>
         <option value="3">Option 3</option>
         </select>
         
         

          

          <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

      </form>

      <table>

        <thead>

          <tr>
            <th>Descrição</th>
            <th>Projeto</th>
            <th>Cliente</th>
           
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

          <tr  v-for="atividade of clientes" :key="atividade.id">
            <td>{{atividade.descricao }}</td>
            <td>{{ atividade.projeto }}</td>
            <td>{{ atividade.cliente }}</td>
            <td>
              <button @click="editar(atividade)" class="waves-effect btn-small blue darken-1"><i class="material-icons">create</i></button>
              <button @click="remover(atividade)"  class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
            </td>

          </tr>

        </tbody>
      
      </table>

    </div>

  </div>
</template>


<script>

import Atividade from '../services/atividade'

export default{

  data(){
    return{ 
      atividade:{
        id:'',
        descricao:'',
        projeto:'',
        cliente:'',
        datacadastro:'',
        datainicio:'',
        dataconclusao:''      
     },
      atividades:[],
      errors:[]
    }
  },
  
  mounted(){
    this.listar()
  },

  methods:{
    
    listar(){
      Atividade.listar().then( resposta=>{
      this.atividades = resposta.data
      console.log(this.atividadess)
      } )
    },

    salvar(){
      if(!this.atividade.id){
        Cliente.salvar(this.atividade).then(resposta=> {
        this.atividade ={}
        alert("Salvo com sucesso")
        this.resposta=resposta
        this.listar()
        this.errors=[]
         }).catch(e=>{
         this.errors = e.response.data.errors
       })
      }
       else{
        Atividade.atualizar(this.atividade).then(resposta=> {
        this.atividade ={}
        alert("Atualizando com sucesso")
        this.resposta=resposta
        this.listar()
        this.errors=[]
         }).catch(e=>{
         this.errors = e.response.data.errors
       })
      }
    },

    editar(atividade){
      this.atividade=atividade
    },

    
    remover(atividade){
     if (confirm('Deseja Excluir a atividade?')){
        Atividade.excluir(atividade).then(resposta=>{
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
