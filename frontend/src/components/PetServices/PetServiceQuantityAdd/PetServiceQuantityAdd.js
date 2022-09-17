import React from 'react';
import {useNavigate} from 'react-router-dom';

const PetServiceQuantityAdd = (props) => {

    const {service, onAddQuantity, AddReservationItem,} = props;

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

        onAddQuantity(service.id.id, quantity);
        AddReservationItem("c2aceb05-99bc-4ecb-90bb-30b4abf6d0c5", service, quantity)

        history("/service");
    }

    return (
        <div className="row mt-5 d-flex justify-content-center">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="quantity" style={{fontSize: 30}}>Quantity</label>
                        <p className="text-secondary">The maximum number of pets to reserve for a service is 5.</p>
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

export default PetServiceQuantityAdd;
