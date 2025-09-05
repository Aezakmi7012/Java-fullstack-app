import React, { useContext } from 'react'
import Menubar from './components/Menubar/Menubar'
import { Navigate, Route, Routes, useLocation } from 'react-router-dom'
import Dashboard from './pages/Dashboard/Dashboard'
import ManageCategory from './pages/ManageCategory/ManageCategory'
import ManageUsers from './pages/ManageUsers/ManageUsers'
import ManageItems from './pages/ManageItems/ManageItems'
import Explore from './pages/Explore/Explore'
import { Toaster } from 'react-hot-toast';
import Login from './pages/Login/Login'
import OrderHistory from './pages/OrderHistory/OrderHistory'
import { AppContext } from './context/AppContext'
import Notfound from './pages/Notfound/Notfound'

const App = () => {

 const location= useLocation();

 const{auth}=useContext(AppContext);

 const LoginRoute=({element})=>{
  if(auth.token)
  {
    return <Navigate to='/dashboard' replace/>
  }

  return element
 }


 const ProtectedRoute=({element,allowedRoles})=>{
  console.log("ProtectedRoute check:", {
    hasToken: !!auth.token,
    role: auth.role,
    allowedRoles: allowedRoles
  });
  
  if(!auth.token)
  {
    console.log("No token, redirecting to login");
    return <Navigate to='/login' replace/>
  }

  if(allowedRoles && !allowedRoles.includes(auth.role))
  {
    console.log("Role not allowed, redirecting to dashboard");
    return <Navigate to='/dashboard' replace/>
  }

  console.log("Access granted, rendering element");
  return element;
 }
  return (
    <div>
      {location.pathname!=="/login" && <Menubar/>}
      <Toaster/>
      <Routes>
        <Route path='/dashboard' element={<ProtectedRoute element={<Dashboard/>} allowedRoles={['ROLE_USER', 'ROLE_ADMIN']}/>}/>
        <Route path='/' element={<ProtectedRoute element={<Dashboard/>} allowedRoles={['ROLE_USER', 'ROLE_ADMIN']}/>}/>
        <Route path='/explore' element={<ProtectedRoute element={<Explore/>} allowedRoles={['ROLE_USER', 'ROLE_ADMIN']}/>}/>
        <Route path='/login' element={<LoginRoute element={<Login/>} />}/>

        {/* Admin Only Routes */}
        <Route path='/category' element={<ProtectedRoute element={<ManageCategory />} allowedRoles={['ROLE_ADMIN']}/>}/>
        <Route path='/users' element={<ProtectedRoute element={<ManageUsers />} allowedRoles={['ROLE_ADMIN']}/> }/>
        <Route path='/items' element={<ProtectedRoute element={<ManageItems />} allowedRoles={['ROLE_ADMIN']}/> }/>
        
      
        
        <Route path='/orders' element={<ProtectedRoute element={<OrderHistory/>} allowedRoles={['ROLE_USER', 'ROLE_ADMIN']}/>}/>        <Route path='*' element={<Notfound/>}/>


      </Routes>
    </div>
  )
}

export default App
