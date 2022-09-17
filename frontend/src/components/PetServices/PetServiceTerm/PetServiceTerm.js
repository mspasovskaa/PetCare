import React from "react";
import {Link} from "react-router-dom";

const PetServiceTerm = (props) => {
    const {service, onEdit} = props;
    return (
        <tr>
            <td>{service.serviceName}</td>
            <td>{service.serviceDescription}</td>
            <td>{service.date}</td>
            <td>{service.time}</td>
            <td>{service.price.amount}</td>
            <td>{service.price.currency}</td>
            <td>
                <Link className={"btn btn-outline-success me-2"}
                      onClick={() => {
                          onEdit(service.id.id)
                      }}
                      to={`/add/quantity/${service.id.id}`}>Reserve</Link>
            </td>
        </tr>
    );
}

export default PetServiceTerm;