import React, { Component } from 'react';

import { Bar } from 'react-chartjs-2';

import Modal from 'react-modal';
Modal.setAppElement('#root')

const customStyles = {
  overlay: {
    zIndex: "100",
    backgroundColor: "rgba(0, 0, 0, 0.5)"
  },
  content: {
    left: "5%",
    width: "90%"
  }
};


class DailyChart extends Component {
  state = {
    open: true,
    graphData : {
      labels: this.props.labels,
      datasets: [
        {
          type: 'line',
          yAxisID: 'y-axis-precipitation2',
          data: [16, 42, 117.5, 90.5, 120.5, 225, 193, 110, 197, 529.5, 156.5, 76.5],
          borderColor: 'rgba(255, 0, 0, 1)',
          label: '株価（円）',
          fill: false,
        },
        {
          type: 'bar',
          yAxisID: 'y-axis-precipitation',
          data: [16, 42, 117.5, 90.5, 120.5, 225, 193, 110, 197, 529.5, 156.5, 76.5],
          backgroundColor: 'rgba(30, 144, 255, 1)',
          label: '出来高',
        }


      ]
    },

    graphOption : {
      responsive: true,
      maintainAspectRatio: false,
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
              axisLine: false,
              max: 600,
              min: 0,
              stepSize: 50,
              callback: function (value, index, values) {
                return `${value}`;
              },
            },
            gridLines: {
              drawOnChartArea: false, 
            }
          },

          {
            id: 'y-axis-precipitation2', // idを追加
            position: 'left', // 表示ポジションの設定を追加
            scaleLabel: {
              display: true,
              labelString: '株価（円）',
            },
            ticks: {
              max: 600,
              min: 0,
              stepSize: 50,
              callback: function (value, index, values) {
                return `${value}`;
              },
            }
          },

        ],
      },
    }

};

  constructor(props) {
    super(props);
  }

  afterOpenModal = () => {
    
  }
 
  closeModal = () => {
  }

  render() {
    return (
      <React.Fragment>

<div>
{this.props.prices}
  </div>

        <Modal
          isOpen={this.props.open}
          onAfterOpen={this.afterOpenModal}
          onRequestClose={this.props.onCloseModal}

          style={customStyles}
        >


      <Bar data={this.state.graphData} options={this.state.graphOption} />



        </Modal>
      </React.Fragment>

    )
  }
}
 
export default DailyChart;



