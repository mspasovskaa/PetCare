import React from "react";
import {Link} from "react-router-dom";

const ReservationItemTerm = (props) => {

    const {reservationItem, onEdit} = props;
    return (
        <tr>
            <td>{reservationItem.serviceName}</td>
            <td>{reservationItem.servicePrice.amount}</td>
            <td>{reservationItem.serviceDate}</td>
            <td>{reservationItem.numberOfPets}</td>
            <td>

                <Link className={"btn btn-outline-danger"}
                      to={`/delete/reservationItem/${reservationItem.id.id}`}>Delete</Link>
            </td>
            <td>
                <Link className={"btn btn-outline-info"}
                      onClick={() => {
                          onEdit(reservationItem.serviceId.id)
                      }}
                      to={`/edit/reservationItem/${reservationItem.serviceId.id}`}>Edit</Link>
            </td>
        </tr>
    );
}

export default ReservationItemTerm;