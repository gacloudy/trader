import React, { Component } from 'react';

class Header extends Component {



  render() {
    return (
        <React.Fragment>

<div style={{"paddingTop": "20px", "paddingRight": "50px", "textAlign": "right"}}>
        <button  className="btn btn-primary" onClick={this.props.onClick}>
          Back To Top
        </button>
      </div>
      <hr/>

</React.Fragment>


    );
  }
}
export default Header;