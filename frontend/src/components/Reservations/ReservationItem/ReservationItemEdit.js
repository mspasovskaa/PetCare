import React from 'react';
import {useNavigate, useParams} from 'react-router-dom';

const ReservationItemEdit = (props) => {

    const {service, onAddQuantity, AddReservationItem} = props;

    const {serviceId} = useParams()
    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        quantity: 0,
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })


    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const quantity = formData.quantity;

        onAddQuantity(serviceId, quantity);
        AddReservationItem("c2aceb05-99bc-4ecb-90bb-30b4abf6d0c5", service, quantity)
        history("/reservation");
    }

    return (
        <div className="row mt-5 d-flex justify-content-center">
            <div className="col-md-5">

                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="quantity" style={{fontSize: 30}}>Edit quantity</label>
                        <p className="text-secondary">The maximum number of pets to reserve for a service is 5.</p>
                        <p className="text-secondary">Your edit will be saved if there is more than a week from your
                            reservation item date.</p>
                        <input type="text"
                               className="form-control"
                               id="quantity"
                               name="quantity"
                               placeholder="Number of pets"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" style={{marginTop: 25}} className="btn btn-success">Submit
                    </button>
                </form>
            </div>
        </div>
    )
}

export default ReservationItemEdit;