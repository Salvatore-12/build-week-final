//SUDDIVIDERE TUTTO IN FILE SEPARATI E UNIRLI USANDO COMBINEREDUCERS!!!!

export const ActionTypes = {
  SET_USER_TOKEN: "SET_USER_TOKEN",
  SET_USER_DATA: "SET_USER_DATA",
  SET_LOADING: "SET_LOADING",
  SET_CLIENTS_DATA: "SET_CLIENTS_DATA"
};

export const setUserToken = (token) => ({
  type: ActionTypes.SET_USER_TOKEN,
  payload: token
});

export const setUserData = (data) => ({
  type: ActionTypes.SET_USER_DATA,
  payload: data
});

export const setLoading = (bool) => ({
  type: ActionTypes.SET_LOADING,
  payload: bool
});

export const setClienstData = (data) => ({
  type: ActionTypes.SET_CLIENTS_DATA,
  payload: data
});

export const getTokenFromLogin = (email, password) => async (dispatch) => {
  const URL = "http://localhost:3001/auth/login";
  const response = await fetch(URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      email: email,
      password: password
    })
  });
  if (response.ok) {
    const data = await response.json();
    dispatch(setUserToken(data.token));
    localStorage.setItem("token", data.token);
    return data.token;
  } else {
    throw new Error("errore");
  }
};

export const fetchUserData = (token) => async (dispatch) => {
  const URL = "http://localhost:3001/users/me";
  const response = await fetch(URL, {
    method: "GET",
    headers: {
      Authorization: "Bearer " + token,
      "Content-Type": "application/json"
    }
  });
  if (response.ok) {
    const data = await response.json();
    dispatch(setUserData(data));
    console.log(data);
    return data;
  } else {
    throw new Error("errore");
  }
};

export const changeName = (token, body) => async (dispatch) => {
  const URL = "http://localhost:3001/users/me";
  try {
    const response = await fetch(URL, {
      method: "PUT",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json"
      },
      body: JSON.stringify(body)
    });
    if (response.ok) {
      const data = await response.json();
      dispatch(setUserData(data));
      console.log(data);
      return data;
    } else {
      throw new Error("errore");
    }
  } catch (error) {
    console.log(error);
  }
};

export const changePassword = (token, password) => async (dispatch) => {
  const URL = "http://localhost:3001/users/me";
  try {
    const response = await fetch(URL, {
      method: "PUT",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ password: password })
    });
    if (response.ok) {
      const data = await response.json();
      dispatch(setUserData(data));
      console.log(data);
      return data;
    }
  } catch (error) {}
};

export const fetchClientsData = (userId) => async (dispatch) => {
  const URL = "http://localhost:3001/users/me";
  //COMPLETARE APPENA VIENE MESSO A DISPOSIZIONE L'ENDPOINT CON LA QUERY GIUSTA
};
