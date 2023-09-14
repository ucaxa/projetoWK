import { http } from './config'

export default {

        listar:()=>{
        return http.get('atividades')
    },

    salvar:(atividade)=>{
        return http.post('atividade',atividade)
    },

    atualizar:(atividade)=>{
        return http.put('atividade',atividade)
    },

    excluir:(atividade)=>{
        return http.delete('atividade', {data:atividade})
    }
}