import React from "react";
import ReservationTerm from "../ReservationTerm/ReservationTerm";

const ReservationList = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <h2 className="text-center text-secondary"><b>Reservation List</b></h2>
            <br/>
            <div className={"d-flex justify-content-center"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Reservation currency</th>
                            <th scope={"col"}>Reservation items</th>
                            <th scope={"col"}>Reservation total</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.reservations.map((term, index) => {
                            return (
                                <tr>
                                    <td>{term.currency}</td>
                                    <td>
                                        <ReservationTerm key={term.id} reservationItemList={term.reservationItemList}
                                                         reservationId={term.id}
                                                         onEdit={props.onEdit}></ReservationTerm>
                                    </td>
                                    <td>{term.total}</td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default ReservationList;