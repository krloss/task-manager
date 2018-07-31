// TODO : Externalizar configuração
const url = 'http://localhost:8080/api/';

export default function(Vue) {
    return function(path) {
        return Vue.resource(url + path +'{/id}',{ },{
            // Carregar propriedades mapeadas como Lazy
            property:{method:'GET', url:url + path +'{/id}{/property}'}
        });
    };
}
