<a name="1.0.0"></a>
## 1.0.0 - 2024-06-12

### Added
- **HU4:** Functional, dates corrected and test
- **HU4:** Progress in HU4
- **HU3:** Test for HU3
- **HU3:** Functional refactoring and changelog update without tests
- **HU3:** Initial creation for Trajectories endpoint, query not yet resolved
- **General:** Instalación de servidor local
- **General:** Carga de base de datos de taxis y de trajectories
- **General:** Creación de CRUD
- **General:** Test de CRUD
- **General:** Estructura de JWT no funcional

### Changed
- **HU2:** Test for HU2
- **HU2:** Pageable HU2

### Fixed
- Removed Mapper, Lombok, manual getter/setter, added query for partial plate, queries succeeded

### Documentation
- Swagger and initial test attempt
- Readme

### Initial Commit
- Git init

### Miscellaneous
- Endpoint getter by plate without pagination or limit
- Entity, repository, mapper, service, controller creation

### Unreleased
- **HU3:** Endpoint creation for trajectories, initial query not resolved
- **HU3:** Functional refactoring, changelog update without tests
- **HU3:** Test for HU3
- **HU4:** Progress in HU4
- **HU4:** Functional, dates corrected and test
- **HU2:** Test for HU2
- **HU2:** Pageable HU2
- Removed Mapper, Lombok, manual getter/setter, added query for partial plate, queries succeeded
- Swagger and initial test attempt
- Readme
- Endpoint getter by plate without pagination or limit
- Entity, repository, mapper, service, controller creation

[Unreleased]: https://github.com/PemRug/DEV014-fleet-management-api/tree/main/src
~\Laboratoria\FLEET MANAGEMENT\fleet-management-api git:[Crud]
git checkout Jwt
Switched to branch 'Jwt'
~\Laboratoria\FLEET MANAGEMENT\fleet-management-api git:[Jwt]
git log
commit ac1399e72e042b77ec7b09e9ce6573b15d9f71b2 (HEAD -> Jwt, origin/Jwt)
Author: PemRug <paolamejia955@gmail.com>
Date:   Tue Jul 2 17:45:10 2024 -0500

    Estructura de Autorización sin resolver 403

commit d3177e8782056bd6958ead9f988660b03e0c0ccd (HEAD -> Crud, origin/Crud)
Author: PemRug <paolamejia955@gmail.com>
Date:   Tue Jul 2 14:27:46 2024 -0500

    Users controller test

commit 34827872f2d9c5f77bc2f973e9fae22db30b1fb2
Author: PemRug <paolamejia955@gmail.com>
Date:   Tue Jul 2 10:59:43 2024 -0500

    Updateuser corregido, permite actualizar solo name y createUser corregido, no permite crear 2 usuarios con el mismo nombre o email
