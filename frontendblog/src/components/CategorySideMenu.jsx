

import React from 'react'
import { useState } from 'react'
import { toast } from 'react-toastify'
import { ListGroup, ListGroupItem } from 'reactstrap'
import { loadAllCategories } from '../services/category-service'
import { Link } from 'react-router-dom'

function CategorySideMenu() {

    const [categories, setCategories]=useState([])
    useState(()=>{
      loadAllCategories().then(data=>{
        console.log(data)
       setCategories([...data]) 
      }).catch(error=>{
          toast.error("error in loading categories")
      })
    },[])
  return (
    <div>
      <ListGroup>
        <ListGroupItem tag={Link} to="/" action={true} className='border-0'>
            All blogs
        </ListGroupItem>
        {categories && categories.map((cat,index)=>{
            return(
                <ListGroupItem tag={Link} to={'/categories/'+cat.categoryId} className='border-0 shadow-0 mt-1' key={index} action={true}>
                    {cat.categoryTitle}
                </ListGroupItem>
            )
        })}
      </ListGroup>
    </div>
  )
}

export default CategorySideMenu

