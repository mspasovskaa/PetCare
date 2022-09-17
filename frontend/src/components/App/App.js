import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import PetServices from "../PetServices/PetServices";
import ReservationService from "../../service/ReservationService";
import PetServiceService from "../../service/PetServiceService";
import ReservationList from "../Reservations/ReservationList/ReservationList";
import ReservationItemDelete from "../Reservations/ReservationItem/ReservationItemmDelete"
import Header from "../Header/header";
import PetServiceQuantityAdd from "../PetServices/PetServiceQuantityAdd/PetServiceQuantityAdd";
import ReservationItemEdit from "../Reservations/ReservationItem/ReservationItemEdit";
import PetCare from "../PetCare/PetCare";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            services: [],
            selectedService: {},
            reservationItemList: [],
            reservationId: undefined,
            total: null,
            reservations: [],
            quantity: 0,
        }

    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="row">
                        <Routes>

                            <Route path="/service" element={<PetServices services={this.state.services}
                                                                         AddReservationItem={this.addReservationItem}
                                                                         reservationId={this.state.reservationId} onEdit={this.getService}/>}/>
                            <Route>
                            </Route>
                            <Route path={"/reservation"}
                                   element={<ReservationList reservations={this.state.reservations}
                                                             total={this.state.total}
                                                             onEdit={this.getService}></ReservationList>}>
                            </Route>
                            <Route path="/add/quantity/:id"
                                   element={<PetServiceQuantityAdd service={this.state.selectedService}
                                                                   onAddQuantity={this.addQuantity}
                                                                   AddReservationItem={this.addReservationItem}/>}/>
                            <Route path="/delete/reservationItem/:reservationItemId"
                                   element={<ReservationItemDelete onDelete={this.deleteReservationItem}/>}></Route>
                            <Route path="/edit/reservationItem/:serviceId"
                                   element={<ReservationItemEdit service={this.state.selectedService}
                                                                 onAddQuantity={this.addQuantity}
                                                                 onAddQuantity={this.addQuantity}
                                                                 AddReservationItem={this.addReservationItem}/>}></Route>
                            <Route path="/petcare" element={<PetCare></PetCare>}></Route>
                        </Routes>

                    </div>
                </main>

            </Router>
        );
    }

    componentDidMount() {
        this.loadServices();
        this.loadAllReservations();
    }

    loadAllReservations() {
        ReservationService.getAllReservation()
            .then((data) => {
                this.setState({
                    reservations: data.data
                })
            });
    }


    loadServices = () => {
        PetServiceService.getPetServices()
            .then((data) => {
                this.setState({
                    services: data.data
                })
            });
    }


    addReservationItem = (id, service, quantity) => {
        ReservationService.addReservationItem(id, service, quantity)
            .then((data) => {
                let reservationItem = data.data
                let listReservationItems = [...this.state.reservationItemList]

                listReservationItems.push(reservationItem)
                this.setState({
                    reservationItemList: listReservationItems,
                })
                this.total = this.getTotalPrice();
                this.reservationId = id
            });
    }
    addQuantity = (id, quantity) => {
        ReservationService.addQuantity(id, quantity)
            .then((data) => {
                this.quantity = data.data
            });

    }

    getSelectedPetService = (serviceId) => {
        PetServiceService.getService(serviceId).then((data) => {
            this.setState({
                selectedSelected: data.data
            })
        });
    }

    loadReservationItems = () => {
        ReservationService.getReservation(this.state.reservationId.id)
            .then((data) => {
                this.setState({
                    reservationItemList: data.data
                })
                this.getTotalPrice(this.state.reservationId);
            });
    }


    getTotalPrice = () => {
        ReservationService.getTotalPrice("c2aceb05-99bc-4ecb-90bb-30b4abf6d0c5")
            .then((data) => {
                this.setState({
                        total: data.data
                    }
                )
            })
    }


    deleteReservationItem = (reservationItemId) => {
        console.log(reservationItemId);
        ReservationService.deleteReservationItem("c2aceb05-99bc-4ecb-90bb-30b4abf6d0c5", reservationItemId)
            .then(
                this.loadReservationItems()
            )
    }

    getService = (id) => {
        PetServiceService.getService(id)
            .then((data) => {
                this.setState({
                    selectedService: data.data,
                })
            });
    }
}

export default App;
