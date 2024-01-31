export const ActionTypes = {
  SET_USER_TOKEN: "SET_USER_TOKEN"
};

export const setUserToken = (token) => ({
  type: ActionTypes.SET_USER_TOKEN,
  payload: token
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
