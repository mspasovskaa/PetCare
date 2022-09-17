import React from "react";
import PetServiceTerm from "./PetServiceTerm/PetServiceTerm";

const PetServices = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <h2 className="text-center text-secondary"><b>Pet Services List</b></h2>
            <br/>
            <div className="block col-8 d-flex justify-content-center" style={{marginLeft: 250}}>
                <div>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Service Name</th>
                                <th scope={"col"}>Service Description</th>
                                <th scope={"col"}>Service Date</th>
                                <th scope={"col"}>Service Time</th>
                                <th scope={"col"}>Service Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            {props.services.map((term) => {
                                console.log(term)
                                return (
                                    <PetServiceTerm key={term.id} service={term}
                                                    AddReservationItem={props.AddReservationItem}
                                                    reservationId={term.reservationId}
                                                    onEdit={props.onEdit}></PetServiceTerm>
                                )
                            })}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default PetServices;