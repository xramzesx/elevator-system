import styled from "styled-components"
import { ElevatorRequest, ElevatorStatus } from "../../../common/types"
import constants from "../../../common/constants"
import ElevatorDoors from "../door/ElevatorDoors"
import ElevatorPanel from "./ElevatorPanel"

const Floor = styled.div`
    position: relative;
    height: ${constants.elevator.car.height};
    border-bottom: 1px solid lightgray;

    display: flex;
    justify-content: center;
`
const FloorNumber = styled.div`
    font-weight: 600;
    font-size: 2rem;

    position: absolute;
    left: 0;
    top: 0;
    
    width: ${constants.elevator.car.height};
    height: ${constants.elevator.car.height};
    
    padding: .5rem 1rem;
    
    display: flex;
    align-items: flex-start;
    justify-content: flex-end;
        
    color: lightgray;
    user-select: none;
    /* z-index: -1; */
`

const Container = styled.div`
    width: 100%;
    max-width: ${constants.ui.maxWidth};
    height: 100%;
    display: grid;
    grid-template-columns: 1fr auto;
`


interface IElevatorFloor {
    floor: number,
    statusList: ElevatorStatus[],
    isHighest?: boolean,
    isLowest?: boolean,
    onPickup: (request: ElevatorRequest) => void
}

const ElevatorFloor = (props: IElevatorFloor) => {
    return (
        <Floor>
            <FloorNumber>
                {props.floor}.
            </FloorNumber>

            <Container>            
                <ElevatorDoors 
                    floor={props.floor} 
                    statusList={props.statusList}             
                />
                <ElevatorPanel
                    floor={props.floor}
                    isHighest={props.isHighest}
                    isLowest={props.isLowest}
                    onPickup={props.onPickup}
                />
            </Container>

        </Floor>
    )
}

export default ElevatorFloor