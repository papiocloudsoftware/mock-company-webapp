import "./style/app.css";

import { createStyles, CssBaseline, WithStyles, withStyles } from "@material-ui/core";
import React from "react";
import Header from "./components/Header";
import Body from "./components/Body";


const styles = () =>
  createStyles({
    content: {
      minWidth: "100%"
    }
  });

interface Product {
  readonly id: number;
  readonly description: string;
  readonly cost: string;
  readonly image: File;
}

interface AppState {
  products: Product[];
}

interface AppProps extends WithStyles<typeof styles> {}

class App extends React.Component<AppProps, AppState> {
  constructor(props: AppProps) {
    super(props);
    this.state = {
      products: []
    }
  }

  componentDidMount() {
    this.getProducts = this.getProducts.bind(this);
    this.loadProducts = this.loadProducts.bind(this);
    this.loadProducts();
  }

  async loadProducts() {
    const response = await fetch("/api/products/list");
    const data = await response.json();
    console.log(data);
    this.setState({ products: data })
  }

  getProducts(products: Product[]) {
    this.setState({ products });
  }

  render() {
    const { products } = this.state;
    return (
      <div id="app">
        <CssBaseline />
        <Header id="header" sendProducts={this.getProducts}/>
        <Body products={products}></Body>
      </div>
    );
  }
}

export default withStyles(styles)(App);
