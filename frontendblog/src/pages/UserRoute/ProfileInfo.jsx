import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { useContext } from 'react'
import { useParams } from 'react-router-dom'
import { Col, Row } from 'reactstrap'
import Base from '../../components/Base'
import userContext from '../../context/userContext'
import { getUser } from '../../services/user-services'

const ProfileInfo =()=> {
  const object=useContext(userContext)
const[user,setUser]=useState(null)
  const {userId}=useParams()

  useEffect(()=>{

    getUser(userId).then(data=>{
console.log(data);
  setUser({...data})

    })
  },[])

  const userView=()=>{

    return(
      <Row>
        <Col md={{size:8,offset:2}}>
         <h1> This is user profile</h1>
        </Col>
      </Row>
    )

  }
  return (
    <Base>
    
   
    <h1>This is {object.user.data.name}'s profile</h1>
    </Base>
  )
}

export default ProfileInfo