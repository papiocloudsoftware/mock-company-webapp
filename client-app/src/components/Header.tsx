import { AppBar, createStyles, Theme, Toolbar, WithStyles, withStyles, WithTheme, withTheme } from "@material-ui/core";
import React from "react";

const styles = () => {
  return createStyles({
    appBar: {
      position: "relative"
    },
    toolBar: {
      height: "100%",
      width: "inherit",
      backgroundColor: "black"
    },
    logoContainer: {
      margin: "auto"
    }
  });
};

interface HeaderProps extends WithStyles<typeof styles>, WithTheme {
  readonly id?: string;
}

class Header extends React.Component<HeaderProps> {

  constructor(props: HeaderProps) {
    super(props);
  }

  render() {
    const classes = this.props.classes;
    return (
      <AppBar id={this.props.id} className={classes.appBar}>
        <Toolbar className={classes.toolBar}>
          <div className={classes.logoContainer}>
            <div className="logo"></div>
          </div>
        </Toolbar>
      </AppBar>
    );
  }
}

export default withTheme(withStyles(styles)(Header));