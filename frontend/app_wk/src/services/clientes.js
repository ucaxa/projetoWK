import { http } from './config'

export default {

        listar:()=>{
        return http.get('clientes')
    },

    salvar:(cliente)=>{
        return http.post('cliente',cliente)
    },

    atualizar:(cliente)=>{
        return http.put('cliente',cliente)
    },

    excluir:(cliente)=>{
        return http.delete('cliente', {data:cliente})
    }
}