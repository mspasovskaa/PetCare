import axios from '../custom-axios/service-axios';

const PetServiceService = {

    getPetServices: () => {
        return axios.get("/service");
    },

    getService: (id) => {
        return axios.get(`/service/${id}`);
    },

}

export default PetServiceService;