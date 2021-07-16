<template>
  <div id="app">

<b-container>

  <b-navbar type="dark" variant="primary">
      <b-navbar-brand tag="h4">Tasks</b-navbar-brand>
  </b-navbar>

      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          <div class="card-panel red lighten-4">
            Campo <b>{{ erro.field }}</b> - {{ erro.defaultMessage }}
          </div>
        </li>
      </ul>

    <b-form @submit.prevent="salvar">

      <b-form-group
        label="Titulo:"
        description="Digite o titulo"
      >
      <b-form-input
          v-model="task.title"
          type="text"
          placeholder="Informe o titulo"
          required
        ></b-form-input>

      </b-form-group>

      <b-form-group label="Status:">
       <b-form-select v-model="task.status" :options="options" required></b-form-select>
      </b-form-group>
      
      <b-button type="submit" variant="success">Salvar</b-button>

    </b-form><br>

    <b-table :fields="colunas" striped hover :items="tasks">

      <template #cell(opcoes)="data">
        <b-button type="button" variant="outline-primary" @click="editar(data.item)">Editar</b-button>
        <b-button type="button" variant="outline-danger" @click="remover(data.item)">Remover</b-button>
      </template>

    </b-table>
   
    </b-container>

  </div>
</template>

<script>

import Task from './services/tasks'

export default {
  name: 'App',
  components: {

  },

  data(){
    return {

      colunas: [
          // A virtual column that doesn't exist in items
          { key: 'id', label: 'Codigo'},
          // A column that needs custom formatting
          { key: 'title', label: 'Titulo' },
          { key: 'status', label: 'Status' },
          'opcoes',
          
      ],

     options: [
          { value: 'STARTED', text: 'Started' },
          { value: 'FINISHED', text: 'Finished' },
      ],
      task: {
        id: '',
        title: '',
        status: '',
      },
      tasks: [],
      errors: [],
    }
  },

  mounted(){
    this.listar()
  },

  methods: {
    salvar(){
      
      if(!this.task.id){

        Task.salvar(this.task).then(()=>{
          this.task = {}
          this.errors = {}
          this.listar()
          alert('Salvo com sucesso!')
        }).catch(e => {
          this.errors = e.response.data.errors
          console.log(e)
        })

      }else{

        Task.atualizar(this.task).then(()=>{
          this.task = {}
          this.errors = {}
          this.listar()
          alert('Atualizado com sucesso!')
        }).catch(e => {
          this.errors = e.response.data.errors
          console.log(e)
        })

      }
      
    },
    listar(){

      Task.listar().then((resposta)=>{
        this.tasks = resposta.data
      }).catch(e => {
          console.log(e)
      })

    },

    editar(task){

      this.task = task
      console.log(this.task)

    },

    remover(task){

      if(confirm(`Deseja realmente excluir ${task.nome}?`)){

         Task.remover(task).then(()=> {
            this.listar()
            this.errors = {}
        }).catch(e => {
            console.log(e)
            this.errors = e.response.data.errors
        })

      }

     

    }
  }
}
</script>

<style>



</style>
