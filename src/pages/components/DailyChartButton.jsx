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


class DailyChartButton extends Component {
  state = {
    open: false,
    graphData: {},
    graphOption: {}
  };

  constructor(props) {
    super(props);
  }

  handleChart= (code, date) => {
    const query_params = new URLSearchParams({code:code, date:date});


    fetch("http://133.125.50.231:8080/react/chart?" + query_params)
    .then(res => res.json())
    .then(
      (result) => {

        const graphData = {
          labels: result.labels,
          datasets: [
            {
              type: 'line',
              yAxisID: 'y-axis-precipitation2',
              data: result.prices,
              borderColor: 'rgba(255, 0, 0, 1)',
              label: '株価（円）',
              fill: false,
            },
            {
              type: 'bar',
              yAxisID: 'y-axis-precipitation',
              data: result.amounts,
              backgroundColor: 'rgba(30, 144, 255, 1)',
              label: '出来高',
            }
          ]
        }

        const graphOption = {
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
                  max: result.maxAmount,
                  min: 0,
                  stepSize: result.stepAmount,
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
                  max: result.maxPrice,
                  min: result.minPrice,
                  stepSize: result.yobine,
                  callback: function (value, index, values) {
                    return `${value}`;
                  },
                }
              },
    
            ],
          },
        };

        this.setState({graphData, graphOption});
        this.setState({open: true});

      },
      // 補足：コンポーネント内のバグによる例外を隠蔽しないためにも
      // catch()ブロックの代わりにここでエラーハンドリングすることが重要です
      (error) => {
          console.log(error);
      }
    );



  }

  afterOpenModal = () => {
    
  }

  closeModal = () => {
    this.setState({open: false});
  }


  render() {
    return (
      <React.Fragment>
        <button  className="btn btn-success" onClick={(code, date) => this.handleChart(this.props.code, this.props.date)}>
          Price Chart
        </button>


        <Modal
          isOpen={this.state.open}
          onAfterOpen={this.afterOpenModal}
          onRequestClose={this.closeModal}
          style={customStyles}
        >


          <Bar data={this.state.graphData} options={this.state.graphOption} />

        </Modal>



      </React.Fragment>

    )
  }
}
 
export default DailyChartButton;



