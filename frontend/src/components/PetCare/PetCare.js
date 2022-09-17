import React from 'react';

const PetCare = (props) => {

    return (
        <div className="row mt-5 d-flex justify-content-center">
            <div className="col-md-5">
                <div>
                    <div>
                        <h1 className={"text-center text-secondary"}><b>Welcome to PetCare!</b></h1>
                    </div>
                    <br/>
                    <div>
                        <img src='https://i.pinimg.com/736x/a5/0e/9c/a50e9ccbf4558fd98376b12f8d4c121b.jpg'
                             style={{width: 100, float: "left"}}></img>
                    </div>
                    <div style={{fontStyle: "italic", fontSize: 20}}>
                        PetCare will enable users to be able to review available pet services and make a reservation for
                        their pets.
                        When a service is published, it will have a description, date, time and price.
                        You can choose what kind of service you need and you can reserve an appointment, where when
                        making an item from the reservation,
                        you will be asked to enter the number of pets. PetCare enables you to create multiple such items
                        for different services and
                        accordingly a different number of pets for each.
                        Once you have chosen the services you want to reserve, you will be shown the total price for all
                        services in you reservation.
                        If you change your mind and want to remove an item from the reservation, you can do the same
                        with one click,
                        as well as if you want to change the number of pets you have selected.
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PetCare;
