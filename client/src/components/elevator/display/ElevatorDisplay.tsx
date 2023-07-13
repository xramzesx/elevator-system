import constants from "src/common/constants"
import { ElevatorStatus } from "@common/types"
import { styled } from "styled-components"
import { useMemo } from "react"

import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import { getDirection } from "src/common/utils";
import { Paper } from "@mui/material";

const Container = styled(Paper)`
    max-width: ${constants.elevator.car.width};
    width: 100%;
    height: 3rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-family: consolas;
    background-color: #ddd;
`

const IconContainer = styled.div`
    height: 1.5rem;
    display: flex;
`

const FloorNumber = styled.div`

`

interface IElevatorDisplay {
    status: ElevatorStatus
}


const icons = {
    UP: (<KeyboardArrowUpIcon />),
    DOWN: (<KeyboardArrowDownIcon/>),
    IDLE: null
}

const ElevatorDisplay = (props: IElevatorDisplay) => {
    const icon = useMemo(() => icons[getDirection(props.status)], [props.status])
    return (
        <Container>
            <IconContainer>
                {icon}
            </IconContainer>
            <FloorNumber>
                {props.status.current}
            </FloorNumber>
        </Container>
    )
}

export default ElevatorDisplay