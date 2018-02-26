const url = 'http://localhost:8080/api/';

export default function(Vue) {
    return function(path) {
        return Vue.resource(url + path +'{/id}',{ },{
            property:{method:'GET', url:url + path +'{/id}{/property}'}
        });
    };
}
