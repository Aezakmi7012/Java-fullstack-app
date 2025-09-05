import { createContext, useEffect, useState } from "react";
import { fetchCategories } from "../Service/CategoryService";
import { fetchItems } from "../Service/ItemService";


export const AppContext = createContext(); 

export const AppContextProvider = ({ children }) => {
    const [categories, setCategories] = useState([]);
    const[auth,setAuth]=useState({token:null,role:null})

    const[itemsData,setItemsData]=useState([])
    const[cartItems,setCartItems]=useState([])

    const addToCart = (item) => {
        const existingItem = cartItems.find(cartItem => cartItem.itemId === item.itemId);
      
        if (existingItem) {
          setCartItems(cartItems.map(cartItem =>
            cartItem.itemId === item.itemId
              ? { ...cartItem, quantity: cartItem.quantity + 1 }
              : cartItem
          ));
        } else {
          setCartItems([...cartItems, { ...item, quantity: 1 }]);
        }
      };
      

    const removeFromCart=(itemId)=>{

        setCartItems(cartItems.filter(item=>item.itemId !==itemId))

    }

    const clearCart=()=>{
        setCartItems([])
    }

    const updateQuantity=(itemId,newQuantity)=>{
        setCartItems(cartItems.map(item=>item.itemId === itemId ? {...item,quantity:newQuantity}:item))


    }

    useEffect(() => {
         async function loadData() {
            try {
                if(localStorage.getItem('token') && localStorage.getItem('role')){
                    console.log("Token found, setting auth data...");
                    setAuthData(
                        localStorage.getItem('token'),
                        localStorage.getItem('role')
                    )
                    
                    // Only fetch data if user is authenticated
                    console.log("Fetching categories and items...");
                    const response = await fetchCategories();
                    const itemResponse= await fetchItems();
                    console.log("Categories response:", response.data);
                    console.log("Items response:", itemResponse.data);
                    setCategories(response.data);
                    setItemsData(itemResponse.data)
                } else {
                    console.log("No token found, user not authenticated");
                }
            } catch (err) {
                console.error("Error fetching categories:", err);
                console.error("Error details:", err.response?.data);
            }
        }

        loadData();
    }, []);

    const setAuthData=(token,role)=>{
        console.log("setAuthData called with:", {token: token ? "TOKEN_EXISTS" : "NO_TOKEN", role});
        setAuth({token,role});
        console.log("Auth state updated");
    }

    const loadUserData = async () => {
        try {
            console.log("loadUserData called");
            const response = await fetchCategories();
            const itemResponse = await fetchItems();
            console.log("loadUserData - Categories:", response.data);
            console.log("loadUserData - Items:", itemResponse.data);
            setCategories(response.data);
            setItemsData(itemResponse.data);
        } catch (err) {
            console.error("Error fetching user data:", err);
            console.error("Error details:", err.response?.data);
        }
    };

    const contextvalue={
        categories,
        setCategories,
        auth,
        setAuthData,
        loadUserData,
        itemsData,
        setItemsData,
        addToCart,
        cartItems,
        removeFromCart,
        updateQuantity,
        clearCart

    }

    return (
        <AppContext.Provider value={contextvalue}>
            {children}
        </AppContext.Provider>
    );
};


