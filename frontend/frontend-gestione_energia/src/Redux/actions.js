export const ActionTypes = {
  SET_USER_TOKEN: "SET_USER_TOKEN",
  SET_USER_DATA: "SET_USER_DATA"
};

export const setUserToken = (token) => ({
  type: ActionTypes.SET_USER_TOKEN,
  payload: token
});

export const setUserData = (data) => ({
  type: ActionTypes.SET_USER_DATA,
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
    console.log(data);
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
  } else {
    throw new Error("errore");
  }
};
