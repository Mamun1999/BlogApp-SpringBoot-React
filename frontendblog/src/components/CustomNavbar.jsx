import { NavLink as ReactLink, useNavigate } from "react-router-dom";
import React, { useContext, useState } from "react";
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
} from "reactstrap";
import { useEffect } from "react";
import { doLogOut, getCurrentUserDetail, isLoggedIn } from "../Auth";
import userContext from "../context/userContext";

function CustomNavbar() {
  const userContextData = useContext(userContext);
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  const [login, setLogin] = useState(false); //is user login or not?//initially user not login//
  const [user, setUser] = useState(undefined); //after login we wre setting

  useEffect(() => {
    setLogin(isLoggedIn());
    setUser(getCurrentUserDetail());
  }, [login]);

  const logout = () => {
    doLogOut(() => {
      setLogin(false);
      userContextData.setUser({
        data: null,
        login: false,
      });
      navigate("/");
    });
  };

  return (
    <div>
      <Navbar color="info" d ark expand="md" fixed="" className="px-5">
        <NavbarBrand tag={ReactLink} to="/">
          Blog
        </NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            {/* //it is bringing left */}

            <NavItem>
              {/* for component showing dynamically we declare NavLink as reactlink and now we use tag={ReactLink} to ="/login" */}
              <NavLink tag={ReactLink} to="/">
                New Feeds
              </NavLink>
            </NavItem>
            <NavItem>
              {/* for component showing dynamically we declare NavLink as reactlink and now we use tag={ReactLink} to ="/login" */}
              <NavLink tag={ReactLink} to="/about">
                About
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/services">
                Services
              </NavLink>
            </NavItem>

            <UncontrolledDropdown inNavbar nav>
              <DropdownToggle caret nav>
                more
              </DropdownToggle>
              <DropdownMenu right>
                <DropdownItem tag={ReactLink} to="/services">
                  Contact Us
                </DropdownItem>
                <DropdownItem>Facebook</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>Instagram</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          <Nav navbar>
            {login && (
              <>
                <NavItem>
                  <NavLink tag={ReactLink} to={`/user/profile-info/${user.id}`}>
                    Profile Info
                  </NavLink>
                </NavItem>

                <NavItem>
                  <NavLink tag={ReactLink} to="/user/dashboard">
                    {user.email}
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink onClick={logout}>Logout</NavLink>
                </NavItem>
              </>
            )}

            {!login && (
              <>
                <NavItem>
                  <NavLink tag={ReactLink} to="/login">
                    Login
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink tag={ReactLink} to="/signup">
                    Signup
                  </NavLink>
                </NavItem>
              </>
            )}
          </Nav>
        </Collapse>
      </Navbar>
    </div>
  );
}

export default CustomNavbar;
