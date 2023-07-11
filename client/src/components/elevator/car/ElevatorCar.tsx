import { ElevatorStatus } from "src/common/types";
import constants from "src/common/constants";
import styled from "styled-components";


interface IElevatorCar {
    status: ElevatorStatus;
    lowestFloor: number;
    highestFloor: number;
}

const ElevatorCar = styled.div<IElevatorCar>`
    position: absolute;
    width: 100%;
    max-width: ${constants.elevator.car.width};
    height: ${constants.elevator.car.height};
    background: ${props => props.status.isOpen ? "blue" : "#003"};
    z-index: -1;
    top: 100%;
    left: 0;

    transform: translateY(${
        props => (props.highestFloor -( props.status.current)) * constants.elevator.car.intHeight
    }rem);

    transition: 1s transform ease;

`

export default ElevatorCar