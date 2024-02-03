//SUDDIVIDERE TUTTO IN FILE SEPARATI E UNIRLI USANDO COMBINEREDUCERS!!!!

export const ActionTypes = {
  SET_USER_TOKEN: "SET_USER_TOKEN",
  SET_USER_DATA: "SET_USER_DATA",
  SET_LOADING: "SET_LOADING",
  SET_CLIENTS_DATA: "SET_CLIENTS_DATA",
  SET_BILLS_DATA: "SET_BILLS_DATA",
  SET_USER_ROLE: "SET_USER_ROLE",
  SET_ALL_USERS: "SET_ALL_USERS",
  SET_ALL_CLIENTS: "SET_ALL_CLIENTS"
};

export const setUserToken = (token) => ({
  type: ActionTypes.SET_USER_TOKEN,
  payload: token
});

export const setAllClients = (token) => ({
  type: ActionTypes.SET_ALL_CLIENTS,
  payload: token
});

export const setAllUsers = (users) => ({
  type: ActionTypes.SET_ALL_USERS,
  payload: users
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

export const setBillsData = (data) => ({
  type: ActionTypes.SET_BILLS_DATA,
  payload: data
});

export const setUserRole = (role) => ({
  type: ActionTypes.SET_USER_ROLE,
  payload: role
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
  const URL = "http://localhost:3001/users/me/updateProfile";
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

export const fetchClientsData = (token) => async (dispatch) => {
  const URL = "http://localhost:3001/users/me/clients";
  try {
    const response = await fetch(URL, {
      method: "GET",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json"
      }
    });
    if (response.ok) {
      const data = await response.json();
      console.log(data);
      dispatch(setClienstData(data));
      return data;
    } else {
      throw new Error("problema nella fetch");
    }
  } catch (error) {
    console.error(error);
  }
};

export const fetchBillsFromClient = (token, clientId) => async (dispatch) => {
  const URL = `http://localhost:3001/Bill/client/${clientId}`;
  try {
    const response = await fetch(URL, {
      method: "GET",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json"
      }
    });
    if (response.ok) {
      const data = await response.json();
      console.log(data);
      dispatch(setBillsData(data));
      return data;
    } else {
      throw new Error("problema nella fetch");
    }
  } catch (error) {
    console.error(error);
  }
};

export const fetchUserRole = (token) => async (dispatch) => {
  const URL = "http://localhost:3001/users/me/admin";
  try {
    const response = await fetch(URL, {
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json"
      }
    });
    if (response.ok) {
      dispatch(setUserRole("ADMIN"));
      return "ADMIN";
    } else {
      if (response.status === 401) {
        dispatch(setUserRole("USER"));
        return "USER";
      } else {
        throw new Error("errore nella fetch");
      }
    }
  } catch (error) {
    console.error(error);
  }
};

export const fetchAllUsers = (token) => async (dispatch) => {
  const URL = `http://localhost:3001/users`;
  try {
    const response = await fetch(URL, {
      method: "GET",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json"
      }
    });
    if (response.ok) {
      const data = await response.json();
      console.log(data);
      dispatch(setAllUsers(data));
      return data;
    } else {
      throw new Error("problema nella fetch");
    }
  } catch (error) {
    console.error(error);
  }
};

export const fetchAllClients = (token) => async (dispatch) => {
  const URL = `http://localhost:3001/users/me/getClients?orderBy=name`;
  try {
    const response = await fetch(URL, {
      method: "GET",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json"
      }
    });
    if (response.ok) {
      const data = await response.json();
      console.log(data);
      dispatch(setAllClients(data));
      return data;
    } else {
      throw new Error("problema nella fetch");
    }
  } catch (error) {
    console.error(error);
  }
};
