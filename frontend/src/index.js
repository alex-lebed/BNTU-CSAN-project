import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import thunk from "redux-thunk";
import { createStore, applyMiddleware } from "redux";
import * as serviceWorker from "./serviceWorker";
import { Provider } from "react-redux";
import data from "./store/initialState";
import rootReducer from "./store/Reducers";
import { connect } from "./SocketClient";

connect();

const initialState = localStorage["redux-store"]
  ? JSON.parse(localStorage["redux-store"])
  : data;

const saveState = () => {
  localStorage["redux-store"] = JSON.stringify(store.getState());
};

const loadState = () => {};

const store = applyMiddleware(thunk)(createStore)(rootReducer, initialState);
store.subscribe(saveState);
store.subscribe(loadState);

window.React = React;
window.store = store;

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);

serviceWorker.unregister();
