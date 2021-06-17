import "./style/app.css";

import { createStyles, CssBaseline, MuiThemeProvider, WithStyles, withStyles } from "@material-ui/core";
import React from "react";
import Header from "./components/Header";
import Body from "./components/Body";


const styles = () =>
  createStyles({
    content: {
      minWidth: "100%"
    }
  });

interface AppProps extends WithStyles<typeof styles> {}

class App extends React.Component<AppProps> {
  constructor(props: AppProps) {
    super(props);
  }

  render() {
    return (
      <div id="app">
        <CssBaseline />
        <Header id="header" />
        <Body></Body>
      </div>
    );
  }
}

export default withStyles(styles)(App);