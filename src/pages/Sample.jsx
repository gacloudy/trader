import React, { Component } from 'react';

import { withRouter } from 'react-router';

import Header from './components/Header';
import { Bar } from 'react-chartjs-2';
import '../App.css';

import Modal from 'react-modal';
Modal.setAppElement('#root')

const customStyles = {
  overlay: {
    zIndex: "100",
    backgroundColor: "rgba(0, 0, 0, 0.5)"
  },
  content: {
    left: "5%",
    width: "90%",
  }
};

class Sample extends Component {
  state = {
    code: -1,
    dateFrom: "",
    dateTo: "",
    modalIsOpen: false,
    graphData : {
      labels: [
        16, 42, 117.5, 90.5, 120.5, 225, 193, 110, 197, 529.5, 156.5, 76.5
      ],
      datasets: [
        {
          type: 'bar',
          yAxisID: 'y-axis-precipitation',
          data: [16, 42, 117.5, 90.5, 120.5, 225, 193, 110, 197, 529.5, 156.5, 76.5],
          backgroundColor: 'rgba(30, 144, 255, 1)',
          label: '出来高',
        },
        {
          type: 'line',
          yAxisID: 'y-axis-precipitation2',
          data: [16, 42, 117.5, 90.5, 120.5, 225, 193, 110, 197, 529.5, 156.5, 76.5],
          borderColor: 'rgba(255, 0, 0, 1)',
          label: '株価（円）',
          fill: false,
        }


      ],
    },

    graphOption : {
      scales: {
        xAxes: [
          {
            scaleLabel: {
              display: false
            },
          },
        ],
        yAxes: [
          {
            id: 'y-axis-precipitation', // idを追加
            position: 'right', // 表示ポジションの設定を追加
            scaleLabel: {
              display: true,
              labelString: '出来高',
            },
            ticks: {
              max: 800,
              min: 0,
              stepSize: 50,
              callback: function (value, index, values) {
                return `${value}`;
              },
            },
          },

          {
            id: 'y-axis-precipitation2', // idを追加
            position: 'left', // 表示ポジションの設定を追加
            scaleLabel: {
              display: true,
              labelString: '株価（円）',
            },
            ticks: {
              max: 800,
              min: 0,
              stepSize: 50,
              callback: function (value, index, values) {
                return `${value}`;
              },
            },
          },

        ],
      },
    }



};


handleToHome = () => {
  this.props.history.push('/')
}


  handleSearch = () => {

    this.setState({modalIsOpen: true});

  }


  openModal = () => {
    this.setState({modalIsOpen: true});
  }
  afterOpenModal = () => {
    
  }
 
  closeModal = () => {
    console.log(111);
    this.setState({modalIsOpen: false});
  }


  render() {
    return (
      <React.Fragment>

<Header onClick={this.handleToHome} />

        <div style={{"padding": "10px 50px 50px 50px"}}>


            <div style={{"paddingTop": "10px", "paddingBottom": "10px"}}>
                <button  className="btn btn-success" onClick={this.handleSearch}>
                  Search
                </button>
            </div>




        </div>


        <Modal
        isOpen={this.state.modalIsOpen}
        onRequestClose={this.closeModal}
        onAfterOpen={this.afterOpenModal}
        style={customStyles}
        >


<button  className="btn btn-success" onClick={this.closeModal}>
                  Close
                </button>


                <div style={{height:"80%"}}>
      <Bar data={this.state.graphData} options={this.state.graphOption} />
    </div>



      </Modal>

        </React.Fragment>

    )
  }
}



export default withRouter(Sample)