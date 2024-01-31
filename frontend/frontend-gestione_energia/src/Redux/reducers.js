import { ActionTypes } from "./actions";

const initialstate = {
  token: localStorage.getItem("token") || null
};

const reducer = (state = initialstate, action) => {
  switch (action.type) {
    case ActionTypes.SET_USER_TOKEN:
      return { ...state, token: action.payload };
    default:
      return state;
  }
};

export default reducer;
