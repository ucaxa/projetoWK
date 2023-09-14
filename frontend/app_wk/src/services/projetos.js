import { http } from './config'

export default {

        listar:()=>{
        return http.get('projetos')
    },

    salvar:(projeto)=>{
        return http.post('projeto',projeto)
    },

    atualizar:(projeto)=>{
        return http.put('projeto',projeto)
    },

    excluir:(projeto)=>{
        return http.delete('projeto', {data: projeto})
    }
}