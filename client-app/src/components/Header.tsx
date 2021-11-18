import { AppBar, Button, createStyles, TextField, Toolbar, WithStyles, withStyles, WithTheme, withTheme } from "@material-ui/core";
import React from "react";

const styles = () => {
  return createStyles({
    appBar: {
      position: "relative"
    },
    toolBar: {
      height: "100%",
      width: "inherit",
      backgroundColor: "pink",
      justifyContent: "space-evenly"
    },
    searchContainer: {
      display: "inline-flex",
  },
  searchInput: {
      color: "black",
      width: "50vw",
      backgroundColor: "white",
      borderTopLeftRadius: "8px",
      borderBottomLeftRadius: "8px",
  },
  searchButton: {
    width: "fit-content",
    border: "solid 2px gray",
    borderLeft: "none"

  },
  showAllBtn: {
    margin: "0 0 0 3vw"
  },
    logoContainer: {

    }
  });
};

interface HeaderProps extends WithStyles<typeof styles>, WithTheme {
  readonly id?: string;
  sendProducts: Function;
}

interface Product {
  readonly id: number;
  readonly description: string;
  readonly cost: string;
  readonly image: File;
}

interface HeaderState {
  products: Product[];
  searchInput: string;
}

class Header extends React.Component<HeaderProps, HeaderState> {

  constructor(props: HeaderProps) {
    super(props);
    this.state = {
      products: [],
      searchInput: ""
    }
  }

  async componentDidMount() {
    this.search = this.search.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleKeypress = this.handleKeypress.bind(this);
  }

  handleInputChange(e: any) {
    const { value } = e.target;
    this.setState({ searchInput: value })
  }

  async search() {
    const { sendProducts } = this.props;
    const { searchInput } = this.state;

    const response = await fetch(`/api/products/search?query=${encodeURIComponent(searchInput)}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    });
    const searchResult = await response.json();
    sendProducts(searchResult);
    this.setState({ products: searchResult });
  }

  handleKeypress(e: any) {
    //it triggers by pressing the enter key
    if (e.keyCode === 13) {
      this.search();
    }
  };

  render() {
    const classes = this.props.classes;
    const { searchInput } = this.state;
    return (
      <AppBar id={this.props.id} className={classes.appBar}>
        <Toolbar className={classes.toolBar}>
          <div className={classes.logoContainer}>
            <div className="logo"></div>
          </div>
          <div className={classes.searchContainer}>
          <TextField 
            label="fast shop..." 
            className={`search-input ${classes.searchInput}`} 
            name="searchInput"
            value={searchInput} 
            onChange={this.handleInputChange}
            onKeyUp={this.handleKeypress}
          />
          <Button 
            variant="outlined" 
            size="small" 
            className={`${classes.searchButton} mag`}
            onClick={this.search}
          >
          </Button>
        </div> 
        </Toolbar>
      </AppBar>
    );
  }
}
export default withTheme(withStyles(styles)(Header));
