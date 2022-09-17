import React from 'react';
import {useNavigate, useParams} from 'react-router-dom';

const ReservationItemDelete = (props) => {
    const { onDelete } = props;
    const history = useNavigate();
    const {reservationItemId} = useParams()
    const handleClick = () => {
        history("/reservation");
        props.onDelete(reservationItemId);
    };
    return(

   <div  className="text-center" style={{marginTop:200}}>
       <div>
           <h5 style={{fontSize:30}}>Are you sure you want to delete this reservation item?</h5>

       </div>
       <br/>
       <div>
           <button className={"btn btn-danger"}
                   onClick={()=> {
                       handleClick()}}>Delete</button>
       </div>
        </div>

    );
}

export default ReservationItemDelete;