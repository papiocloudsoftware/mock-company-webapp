import { createStyles, WithStyles, withStyles, WithTheme, withTheme } from "@material-ui/core";
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

interface Product {
  readonly id: number;
  readonly description: string;
  readonly cost: string;
  readonly image: File;
}

interface BodyProps extends WithStyles<typeof styles>, WithTheme {
  products: Product[]
}

interface BodyState {
  page: number;
  rowsPerPage: number;
}

class Body extends React.Component<BodyProps, BodyState> {

  constructor(props: BodyProps) {
    super(props);
    this.state = {
      page: 0,
      rowsPerPage: 3,
    };
  }

  async componentDidMount() {
    this.handleChangePage = this.handleChangePage.bind(this);
    this.handleChangeRowsPerPage = this.handleChangeRowsPerPage.bind(this);
  }

  handleChangePage(e: any, newPage: any) {
    this.setState({ page: newPage });
  };

  handleChangeRowsPerPage(e: any) {
    this.setState({ rowsPerPage: parseInt(e.target.value), page: 0 })
  };

  render() {
    const classes = this.props.classes;
    const { products } = this.props;
    // products.forEach(async (p) => await import(`${p.image}`));
    const { page, rowsPerPage } = this.state;
    return (
      <div className={classes.body}>
        <Paper>
            <TableContainer>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Cost</TableCell>
                            <TableCell align="right">Image</TableCell>
                            <TableCell align="right">Description</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                    {(rowsPerPage > 0
                        ? products.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                        : products
                        ).map((product: any) => (
                            <TableRow key={product.id}>
                              <TableCell component="th" scope="row">${product.cost}</TableCell>
                              <TableCell align="right"><div className={`image${product.id}`}></div></TableCell>
                              <TableCell align="right">{product.description}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
            className={classes.pagination}
            rowsPerPageOptions={[3, 5, 10]}
            component="div"
            count={products.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onChangePage={this.handleChangePage}
            onChangeRowsPerPage={this.handleChangeRowsPerPage}
            />
        </Paper>
      </div>
    );
  }
}

export default withTheme(withStyles(styles)(Body));