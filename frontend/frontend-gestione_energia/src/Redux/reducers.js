import { ActionTypes } from "./actions";

const initialstate = {
  token: localStorage.getItem("token") || null,
  clientsData: null,
  billsData: null,
  data: null,
  loading: true
};

const reducer = (state = initialstate, action) => {
  switch (action.type) {
    case ActionTypes.SET_USER_TOKEN:
      return { ...state, token: action.payload };
    case ActionTypes.SET_USER_DATA:
      return { ...state, data: action.payload };
    case ActionTypes.SET_LOADING:
      return { ...state, loading: action.payload };
    case ActionTypes.SET_CLIENTS_DATA:
      return { ...state, clientsData: action.payload };
    case ActionTypes.SET_BILLS_DATA:
      return { ...state, billsData: action.payload };
    default:
      return state;
  }
};

export default reducer;
