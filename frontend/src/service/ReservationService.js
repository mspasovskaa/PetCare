import axios from '../custom-axios/reservation-axios';

const ReservationService = {

    getReservation: (id) => {
        return axios.get(`/reservation/${id}`);
    },

    addReservationItem: (id, service, quantity) => {
        return axios.post(`/reservation/addReservationItem/${id}`, {
            "service": service,
            "quantity": quantity
        });
    },


    getTotalPrice: (id) => {
        return axios.get(`/reservation/getReservationTotalPrice/${id}`);
    },
    getAllReservation: () => {
        return axios.get("/reservation");
    },

    addQuantity: (id, quantity) => {
        return axios.post(`/addReservationItemQuantity/${id}`, {
            "quantity": quantity
        });
    },


    deleteReservationItem: (id, reservationItemId) => {
        console.log(reservationItemId);
        return axios.post(`/reservation/deleteReservationItem/${id}/${reservationItemId}`)
    },
}

export default ReservationService;