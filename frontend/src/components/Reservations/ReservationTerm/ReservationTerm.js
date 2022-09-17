import React from "react";
import ReservationItemTerm from "../ReservationItem/ReservationItemTerm";

const ReservationTerm = (props) => {
    const {reservationItemList, reservationId, onDelete} = props;
    return (
        <aside className="block col-3">
            <div>
                <table className={"table table-striped"}>
                    <thead>
                    <tr>
                        <th scope={"col"}>Service Name</th>
                        <th scope={"col"}>Service Price</th>
                        <th scope={"col"}>Service Date</th>
                        <th scope={"col"}>Number od pets</th>
                    </tr>
                    </thead>
                    <tbody>
                    {reservationItemList.map((term) => {
                        return (
                            <ReservationItemTerm key={term.id} reservationItem={term}
                                                 reservationId={reservationId} onEdit={props.onEdit}
                            />
                        );
                    })}
                    </tbody>
                </table>
            </div>
        </aside>
    );
}

export default ReservationTerm;