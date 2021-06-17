import { Button, createStyles, Theme, TextField, WithStyles, withStyles, WithTheme, withTheme } from "@material-ui/core";
import React from "react";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import TablePagination from '@material-ui/core/TablePagination';


const styles = () => {
  return createStyles({
    body: {
        position: "relative"
    },
    searchContainer: {
        textAlign: "center",
        display: "grid",
        width: "35vw",
        margin: "1rem auto"
    },
    searchInput: {
        color: "black",
        margin: "1rem"
    },
    searchButton: {

    },
    moviesContainer: {

    },
    movies: {

    },
    table: {
      width: 650,
      margin: "auto",
      marginTop: "5vh"
    },
    footer: {
      display: "inline-flex"
    },
    showAllBtn: {
      margin: "auto",
      width: "50vw",
      textAlign: "center",
      cursor: "pointer"
    },
    pagination: {
      width: "50vw",
    }
  });
};

interface BodyProps extends WithStyles<typeof styles>, WithTheme {}

interface Movie {
    readonly id: number;
    readonly name: string;
    readonly year: string;
}

interface BodyState {
  movies: Movie[];
  page: number;
  rowsPerPage: number;
  searchInput: string;
}

class Header extends React.Component<BodyProps, BodyState> {

  constructor(props: BodyProps) {
    super(props);
    this.state = {
      movies: [],
      page: 0,
      rowsPerPage: 3,
      searchInput: ""
    };
  }

  async componentDidMount() {
    this.handleChangePage = this.handleChangePage.bind(this);
    this.handleChangeRowsPerPage = this.handleChangeRowsPerPage.bind(this);
    this.getMovies = this.getMovies.bind(this);
    this.search = this.search.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.getMovies();
  }

  async getMovies() {
    const response = await fetch(`/api/movies`);
    const data = await response.json();
    this.setState({ movies: data.movies, page: 0 })
  }

  handleChangePage(e: any, newPage: any) {
    this.setState({ page: newPage });
  };

  handleChangeRowsPerPage(e: any) {
    this.setState({ rowsPerPage: parseInt(e.target.value), page: 0 })
  };

  handleInputChange(e: any) {
    const { value } = e.target;
    this.setState({ searchInput: value })
  }

  async search() {
    const { searchInput } = this.state;
    const response = await fetch("/api/search", {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: searchInput
    });
    const searchResult = await response.json();
    this.setState({ movies: searchResult });
  }

  render() {
    const classes = this.props.classes;
    const { movies, page, rowsPerPage, searchInput } = this.state;
    return (
      <div className={classes.body}>
        <div className={classes.searchContainer}>
          <TextField 
            label="enter movie title" 
            className={`search-input ${classes.searchInput}`} 
            name="searchInput"
            value={searchInput} 
            onChange={this.handleInputChange}
          />
          <Button variant="outlined" size="large" className={classes.searchButton} onClick={this.search}>
            Search Movies
          </Button>
        </div>
        <Paper>
            <TableContainer>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Id</TableCell>
                            <TableCell align="right">Name</TableCell>
                            <TableCell align="right">Year</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                    {(rowsPerPage > 0
                        ? movies.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                        : movies
                        ).map((movie) => (
                            <TableRow key={movie.id}>
                                <TableCell component="th" scope="row">{movie.id}</TableCell>
                                <TableCell align="right">{movie.name}</TableCell>
                                <TableCell align="right">{movie.year}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <div className={classes.footer}>
              <div 
                className={classes.showAllBtn} 
                onClick={this.getMovies} 
                onKeyUp={this.getMovies}>
                  Show All Movies
              </div>
              <TablePagination
              className={classes.pagination}
              rowsPerPageOptions={[3, 5, 10]}
              component="div"
              count={movies.length}
              rowsPerPage={rowsPerPage}
              page={page}
              onChangePage={this.handleChangePage}
              onChangeRowsPerPage={this.handleChangeRowsPerPage}
              />
            </div>
        </Paper>
      </div>
    );
  }
}

export default withTheme(withStyles(styles)(Header));