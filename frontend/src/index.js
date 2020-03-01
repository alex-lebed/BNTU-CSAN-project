import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import thunk from "redux-thunk";
import { createStore, applyMiddleware } from "redux";
import * as serviceWorker from "./serviceWorker";
import { Provider } from "react-redux";
import initialState from "./store/initialState";
import rootReducer from "./store/Reducers";

const store = createStore(rootReducer, initialState, applyMiddleware(thunk));

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);

serviceWorker.unregister();
