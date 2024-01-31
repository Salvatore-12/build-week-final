import { ActionTypes } from "./actions";

const initialstate = {
  token: localStorage.getItem("token") || null,
  data: null
};

const reducer = (state = initialstate, action) => {
  switch (action.type) {
    case ActionTypes.SET_USER_TOKEN:
      return { ...state, token: action.payload };
    case ActionTypes.SET_USER_DATA:
      return { ...state, data: action.payload };
    default:
      return state;
  }
};

export default reducer;
