import { http } from './config'

export default {

    salvar: (task) => {
        return http.post('task', task)
    },

    listar: () => {
        return http.get('tasks')
    },

    atualizar: (task) => {
        return http.put(`task`, task)
    },

    remover: (task) => {
        return http.delete(`task/${task.id}`)
    }

}