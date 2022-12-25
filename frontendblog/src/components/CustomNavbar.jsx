import { NavLink as ReactLink } from 'react-router-dom';
import React, { useState } from 'react';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
} from 'reactstrap';

function CustomNavbar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  return (
    <div>
      <Navbar color="success"
                dark
                expand="md"
                fixed=""
                className="px-5">
        <NavbarBrand tag={ReactLink} to="/">
      Blog
    </NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              {/* for component showing dynamically we declare NavLink as reactlink and now we use tag={ReactLink} to ="/login" */}
              <NavLink tag={ReactLink} to="/about">About</NavLink>
            </NavItem>
            <NavItem>
            <NavLink tag={ReactLink} to="/login">Login</NavLink>
            </NavItem>
            <NavItem>
            <NavLink tag={ReactLink} to="/signup">Signup</NavLink>
            </NavItem>
            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                Options
              </DropdownToggle>
              <DropdownMenu right>
                <DropdownItem tag={ReactLink} to="/services">Services</DropdownItem>
                <DropdownItem>Contact</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>Facebook</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          <NavbarText>Welcome!</NavbarText>
        </Collapse>
      </Navbar>
    </div>
  );
}

export default CustomNavbar;